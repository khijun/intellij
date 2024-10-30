package com.example.demo.fileuploadboard.entity;

import com.example.demo.member.entity.Member;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name= "t_board")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardIdx;
	
	private String title;
	
	private String contents;

	@ColumnDefault("0") //default 0
	private Integer hitCnt;

//	private String creatorId;
	
	private String createdDatetime;

	@ManyToOne
	@JoinColumn(name = "updater_id")
	private Member updaterId;
	
	private String updatedDatetime;

	@Column(columnDefinition = "varchar(2) default 'N'")
	private String deletedYn;

	@ManyToOne
	@JoinColumn(name = "creator_id")
	private Member member;

	@OneToMany(mappedBy = "board")
	private List<BoardFile> files;
}
