package com.example.demo.fileuploadboard.entity;

import com.example.demo.member.entity.Member;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_file")
public class BoardFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;
    @ManyToOne
    private Board board;
    private String originalFileName;
    private String storedFilePath;
    private long fileSize;

    //	private String creatorId;
    @ManyToOne
    private Member member;
    private LocalDateTime createdDatetime;
    //	private String updatorId;
    @ManyToOne
    private Member updator;
    private LocalDateTime updatedDatetime;

    @Column(columnDefinition = "varchar(2) default 'N'")
    private String deletedYn;

}
