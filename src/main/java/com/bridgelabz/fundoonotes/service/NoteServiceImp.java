package com.bridgelabz.fundoonotes.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.NoteDTO;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exception.FundooException;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utils.TokenService;

@Service
public class NoteServiceImp implements NoteService{

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Override
	public void createNote(String token, NoteDTO noteDto) {
		User user = getUser(tokenService.decodeToken(token));
		Note note = new Note();
		BeanUtils.copyProperties(noteDto, note);
		Note savedNote = noteRepository.save(note);
		user.getNotes().add(savedNote);
		userRepository.save(user);
	}
	
	

	@Override
	public List<Note> getNotes(String token) {
		User user = getUser(tokenService.decodeToken(token));
		return user.getNotes();
	}
	
	public User getUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(
                () -> new FundooException(HttpStatus.NOT_FOUND.value(), "User Not Found"));
	}



	@Override
	public void createNote(String token, List<NoteDTO> noteDto) {
		User user = getUser(tokenService.decodeToken(token));
		noteDto.forEach(noteDTO ->{
			Note note = new Note();
			BeanUtils.copyProperties(noteDTO, note);
			Note savedNote = noteRepository.save(note);
			user.getNotes().add(savedNote);
			userRepository.save(user);
		});
	}

}