package com.danieleForte.medievalBattle.service;

import com.danieleForte.medievalBattle.exception.InvalidInputException;
import com.danieleForte.medievalBattle.exception.ResourceNotFoundException;
import com.danieleForte.medievalBattle.entity.Character;
import com.danieleForte.medievalBattle.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CharacterService {

	@Autowired
	private CharacterRepository repository;

	public Character create( Character character ) {
		character.setCreatedAt( LocalDateTime.now( ) );
		character.setActive( true );
		return this.repository.save( character );
	}

	public List< Character > findAll( ) {
		return repository.findAll( );
	}

	public Character findById( Long id ) {
		return repository.findById( id )
						 .orElseThrow( ( ) -> new ResourceNotFoundException(
								 "Character not found with ID: " + id ) );
	}

	public void delete( Long id ) {
		repository.deleteById( id );
	}

	public Character update( Character character ) {
		if ( character.getId( ) == null ) {
			throw new InvalidInputException( "There is no ID" );
		}
		return repository.save( character );
	}
}
