package exercise.domain;

import io.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import io.ebean.annotation.NotNull;

@Entity
public class Article extends Model {

    @Id
    private long id;

    private String title;

    @Lob
    private String body;

    @ManyToOne
    @NotNull
    private Category category;

    // BEGIN
    
    // END
}
