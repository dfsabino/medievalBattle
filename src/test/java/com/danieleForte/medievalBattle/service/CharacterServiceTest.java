package com.danieleForte.medievalBattle.service;
import com.danieleForte.medievalBattle.enums.CharacterType;
import com.danieleForte.medievalBattle.entity.Character;
import com.danieleForte.medievalBattle.repository.CharacterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CharacterServiceTest {

	@InjectMocks
	private CharacterService service;

	@Mock
	private CharacterRepository repository;

	List< Character > characterList;

	@BeforeEach
	void setUp( ) {
		MockitoAnnotations.openMocks( this );
		characterList = new ArrayList<>( );
		Character character1 = new Character( 1L, "Character 1", "Character1 description",CharacterType.HERO,10,10,10,10,2,6,
							   LocalDateTime.now( ), true );

		Character character2 = new Character( 1L, "Character 1", "Character2 description",CharacterType.HERO,10,10,10,10,2,6,
				LocalDateTime.now( ), true );

		characterList.add( character1 );
		characterList.add( character2 );
	}

	@Test
	void create( ) {
		Character character = new Character( 1L, "Character", "Character description", CharacterType.HERO,10,10,10,10,2,6,
				LocalDateTime.now( ), true );

		Character characterExpected = new Character( 1L, "Character", "Character description", CharacterType.HERO,10,10,10,10,2,6,
				LocalDateTime.now( ), true );

		when( repository.save( character ) ).thenReturn( character );
		Character response = service.create( character );
		Assertions.assertEquals( characterExpected.getTitle( ), response.getTitle( ) );
		verify( repository, times( 1 ) ).save( any( ) );
	}

	@Test
	void findAll( ) {
		when( repository.findAll( ) ).thenReturn( characterList );
		List< Character > characters = service.findAll( );
		Assertions.assertEquals( characters, characterList );
		verify( repository, times( 1 ) ).findAll( );
	}

	@Test
	void findById( ) {
		when( repository.findById( any( ) ) ).thenReturn(
				Optional.ofNullable( characterList.get( 0 ) ) );
		Character character = service.findById( 1L );
		Assertions.assertEquals( character, characterList.get( 0 ) );
		verify( repository, times( 1 ) ).findById( any( ) );
	}

	@Test
	void delete( ) {
		doNothing( ).when( repository ).deleteById( any( ) );
		service.delete( 1L );
		verify( repository, times( 1 ) ).deleteById( any( ) );
	}

	@Test
	void update( ) {
		Character character = characterList.get( 0 );
		character.setHitPoint( 6);
		when( repository.save( character ) ).thenReturn( character );
		Character response = service.update( character );
		Assertions.assertEquals( character.getHitPoint( ), response.getHitPoint( ) );
		verify( repository, times( 1 ) ).save( any( ) );
	}
}