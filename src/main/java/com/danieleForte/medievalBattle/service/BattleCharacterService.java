package com.danieleForte.medievalBattle.service;

import com.danieleForte.medievalBattle.entity.Character;
import com.danieleForte.medievalBattle.exception.InvalidInputException;
import com.danieleForte.medievalBattle.exception.ResourceNotFoundException;
import com.danieleForte.medievalBattle.entity.Battle;
import com.danieleForte.medievalBattle.entity.BattleCharacter;
import com.danieleForte.medievalBattle.repository.BattleCharacterRepository;
import com.danieleForte.medievalBattle.repository.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BattleCharacterService {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private BattleCharacterRepository repository;
    @Autowired
    private BattleRepository battleRepository;

    public BattleCharacter create(BattleCharacter battleCharacter ) {

        Battle battle = battleRepository.getBattleByCodeShare(battleCharacter.getCode_share()).orElseThrow( ( ) -> new ResourceNotFoundException(
                "Battle not found with ID Battle: " + battleCharacter.getCode_share() ) );

        int qtdCharacter =  repository.getHasTwoCharacterBattle(battle.getId()).orElseThrow( ( ) -> new ResourceNotFoundException(
                "This battle has already reached the character limit" ) );

        Character character = characterService.findById(battleCharacter.getBattle().getId());
        battleCharacter.setHitPoint(character.getHitPoint());
        battleCharacter.setAgility(character.getAgility());
        battleCharacter.setDefense(character.getDefense());
        battleCharacter.setStrength(character.getStrength());
        battleCharacter.setBattle(battle);
        return this.repository.save( battleCharacter );
    }

    public List<BattleCharacter> getBattleCharacterByBattleId(long battleId)
    {
        return this.repository.getCharacterBattleByBattleId(battleId).orElseThrow( ( ) -> new ResourceNotFoundException(
                "Characteres Battle not found with ID Battle: " + battleId ) );
    }

    public BattleCharacter findById( Long id ) {
        return repository.findById( id )
                .orElseThrow( ( ) -> new ResourceNotFoundException(
                        "Battle Character not found with ID: " + id ) );
    }

    public BattleCharacter update( BattleCharacter battleCharacter ) {
        if ( battleCharacter.getId( ) == null ) {
            throw new InvalidInputException( "There is no ID" );
        }
        return repository.save( battleCharacter );
    }


}
