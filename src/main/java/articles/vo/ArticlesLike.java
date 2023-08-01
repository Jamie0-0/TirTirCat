package articles.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "articles_like", catalog = "FurrEver")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ArticlesLikeId.class)
public class ArticlesLike {
	
	@Id
	@Column
	private Integer like_articles_id;
	@Id
	@Column
	private Integer like_user_id;

}
