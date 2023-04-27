package com.danieleForte.medievalBattle.controller;

import com.danieleForte.medievalBattle.entity.Character;
import com.danieleForte.medievalBattle.service.CharacterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "/character" )
@Api( value = "MedievalBattle API REST" )
@CrossOrigin( origins = "*" )
public class CharacterController {

	@Autowired
	private CharacterService service;

	@GetMapping( "/character" )
	@ApiOperation( "find a Character in DB" )
	public ResponseEntity<List< Character > > getAll( ) {
		return new ResponseEntity<>( service.findAll( ), HttpStatus.OK );
	}


	@GetMapping( "/characters/{id}" )
	@ApiOperation( "find a character by it's id in DB" )
	public ResponseEntity< Character > getById( @PathVariable( value = "id" ) Long characterId ) {
		return new ResponseEntity<>( service.findById( characterId ), HttpStatus.OK );
	}

	@PostMapping( "/characters" )
	@ApiOperation( "Create a new character in DB" )
	public ResponseEntity< Character > create( @RequestBody Character character ) {
		return new ResponseEntity<>( service.create( character ), HttpStatus.CREATED );
	}

	@PutMapping( "/characters" )
	@ApiOperation( "Update a character on DB" )
	public ResponseEntity< Character > update( @RequestBody Character character ) {
		return new ResponseEntity<>( service.update( character ), HttpStatus.OK );
	}

	@DeleteMapping( "/characters" )
	@ApiOperation( "Delete a character on DB" )
	public ResponseEntity< HttpStatus > update( @RequestHeader Long characterId ) {
		service.delete( characterId );
		return new ResponseEntity<>( HttpStatus.NO_CONTENT );
	}
}