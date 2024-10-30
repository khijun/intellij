package com.example.demo.survey;

import com.example.demo.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnsweredData {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ans_id")
	private Long id;

//	@OneToOne
//	@MapsId
//	@JoinColumn(name = "id")
//	private Member member;

	@ElementCollection
	@CollectionTable(
			name = "responses",
			joinColumns = @JoinColumn(name = "MEMBER_ID")
	)
	@OrderColumn
	@Column(name = "seq")
	private List<String> responses;

	@OneToOne
	@JoinColumn(name = "RESPONDENT_ID")
	private Respondent res;

//
//	public List<String> getResponses() {
//		return responses;
//	}
//
//	public void setResponses(List<String> responses) {
//		this.responses = responses;
//	}
//
//	public Respondent getRes() {
//		return res;
//	}
//
//	public void setRes(Respondent res) {
//		this.res = res;
//	}

}
