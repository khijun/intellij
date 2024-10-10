package edu.du.sb_chap17.service;

import edu.du.sb_chap17.dao.IdSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class IdGenerator {

	@Autowired
	IdSequence idSequence;

	public int generateNextId(String sequenceName)
			throws IdGenerationFailedException {

		int id = idSequence.getValueBySequenceName(sequenceName);
		id++;

		idSequence.updateValueBySequenceName(sequenceName, id);
		return id;
	}
}
