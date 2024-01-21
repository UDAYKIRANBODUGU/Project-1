package in.uday.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEnq {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqid;
	private String name;
	private String phno;
	private String classmode;
	private String course;
	private String status;
	
	@CreationTimestamp
	private LocalDateTime doe;
	
	private Integer cid;

}
