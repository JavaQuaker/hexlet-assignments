package exercise.controller;

import java.beans.Encoder;
import java.util.Collections;
import java.util.Map;
import exercise.dto.CartPage;
import exercise.util.NamedRoutes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import exercise.model.CartItem;
import org.eclipse.jetty.util.UrlEncoded;

import io.javalin.http.Context;


public class CartsController {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void index(Context ctx) throws Exception {
        var cookie = ctx.cookie("cart") == null ? "{}" : ctx.cookie("cart");

        Map<String, CartItem> cart = mapper.readValue(
            UrlEncoded.decodeString(cookie),
            new TypeReference<Map<String, CartItem>>() { }
        );

        var page = new CartPage(cart);
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    // BEGIN
    public static void addCart(Context ctx) throws Exception {
        String id = ctx.formParam("id");
        String name = ctx.formParam("name");
        String cookie = ctx.cookie("cart") == null ? "{}" : ctx.cookie("cart");
        Map<String, CartItem> cart = mapper.readValue(UrlEncoded.decodeString(cookie),
                new TypeReference<Map<String, CartItem>>() {
        });
        if (!cart.containsKey(id)) {
            cart.put("id", new CartItem(name, 0));
        } else {
            cart.get(id).increaseCount();
        }

        String encodedString = UrlEncoded.encodeString(mapper.writeValueAsString(cart));
        ctx.cookie("cart", encodedString);
        ctx.redirect(NamedRoutes.rootPath());

    }
    public static void removeCount(Context ctx) throws Exception {
        String cart = mapper.writeValueAsString("{}");
        ctx.cookie("cart", cart);
        ctx.render(NamedRoutes.rootPath());
    }
    // END
}
