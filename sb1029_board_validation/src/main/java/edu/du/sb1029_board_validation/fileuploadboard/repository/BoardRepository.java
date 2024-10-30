package edu.du.sb1029_board_validation.fileuploadboard.repository;

import edu.du.sb1029_board_validation.fileuploadboard.entity.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    public List<edu.du.sb1029_board_validation.fileuploadboard.entity.Board> findAllByOrderByBoardIdxDesc(Pageable pageable);
}
