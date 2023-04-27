package com.danieleForte.medievalBattle.service;

import com.danieleForte.medievalBattle.exception.InvalidInputException;
import com.danieleForte.medievalBattle.exception.ResourceNotFoundException;
import com.danieleForte.medievalBattle.entity.BattleTurn;
import com.danieleForte.medievalBattle.repository.BattleTurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BattleTurnService {
    @Autowired
    private BattleTurnRepository repository;

    public BattleTurn create(BattleTurn battleTurn ) {

        return this.repository.save( battleTurn );
    }

    public BattleTurn update(BattleTurn battleTurn ) {
        if ( battleTurn.getId( ) == null ) {
            throw new InvalidInputException( "There is no ID" );
        }
        return repository.save( battleTurn );
    }

    public BattleTurn getLastAttackByBattleId(long id  ) {

        return repository.getLastAttackByBattleId( id) .orElseThrow( ( ) -> new ResourceNotFoundException(
                "Character not found with ID: " + id ) );
    }

    public BattleTurn getLastDefenseByBattleId(long id ) {

        return repository.getLastDefenseByBattleId( id ).orElseThrow( ( ) -> new ResourceNotFoundException(
                "Character not found with ID: " + id ) );
    }

    public List< BattleTurn > findAll( ) {
        return repository.findAll( );
    }

    public int getQtdTurn(long battleId ) {
        return repository.getQtdTurn( battleId );
    }
}
