package com.danieleForte.medievalBattle.controller;

import com.danieleForte.medievalBattle.entity.Battle;
import com.danieleForte.medievalBattle.entity.BattleCharacter;
import com.danieleForte.medievalBattle.entity.BattleTurn;
import com.danieleForte.medievalBattle.entity.Character;
import com.danieleForte.medievalBattle.entity.TurnInfo;
import com.danieleForte.medievalBattle.model.BattleCharacterModel;
import com.danieleForte.medievalBattle.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( value = "/battle" )
@Api( value = "MedievalBattle API REST" )
@CrossOrigin( origins = "*" )
public class BattleController {

    @Autowired
    private BattleService service;
    @Autowired
    private BattleCharacterService battleCharacterService;
    @Autowired
    private AttackService attackService;
    @Autowired
    private DefenseService defenseService;
    @Autowired
    private DamageService damageService;
    @Autowired
    private InitiativeService initiativeService;

    @PostMapping( "/battle" )
    @ApiOperation( "Create a new battle in DB" )
    public ResponseEntity< Battle > create( ) {
        return new ResponseEntity<>( service.create( ), HttpStatus.CREATED );
    }

    @GetMapping( "/battle/{codeShare}" )
    @ApiOperation( "find a character by it's id in DB" )
    public ResponseEntity< Battle > getById( @PathVariable( value = "codeShare" ) String codeShare ) {
        return new ResponseEntity<>( service.findByCodeShare( codeShare ), HttpStatus.OK );
    }

    @PostMapping( "/chooseBattleCharacter" )
    @ApiOperation( "Choose a character and battle in DB" )
    public ResponseEntity< BattleCharacter > chooseBattleCharacter( @RequestBody BattleCharacterModel battleCharacterModel ) {
        BattleCharacter battleCharacter = new BattleCharacter();
        battleCharacter.setName(battleCharacterModel.getName());
        battleCharacter.setCode_share(battleCharacterModel.getCode_share());
        Character character = new Character();
        character.setId(battleCharacterModel.getCharacterId());
        battleCharacter.setCharacter(character);
        return new ResponseEntity<>( battleCharacterService.create( battleCharacter ), HttpStatus.CREATED );
    }

    @PostMapping( "/attack" )
    @ApiOperation( "Attack character" )
    public ResponseEntity< BattleTurn > attack( @RequestBody TurnInfo turnInfo ) {
        return new ResponseEntity<>( attackService.attack( turnInfo ), HttpStatus.OK );
    }

    @PostMapping( "/defense" )
    @ApiOperation( "Defense character" )
    public ResponseEntity< BattleTurn > defense( @RequestBody TurnInfo turnInfo ) {
        return new ResponseEntity<>( defenseService.defense( turnInfo ), HttpStatus.OK );
    }

    @PostMapping( "/calculateDamage" )
    @ApiOperation( "Damage calculation" )
    public ResponseEntity< BattleTurn > damageCalculation( @RequestBody TurnInfo turnInfo  ) {
        return new ResponseEntity<>( damageService.damage( turnInfo ), HttpStatus.OK );
    }

    @PostMapping( "/initiative" )
    @ApiOperation( "Initiative play" )
    public ResponseEntity< BattleTurn > initiative( @RequestBody TurnInfo turnInfo  ) {
        return new ResponseEntity<>( initiativeService.initiative( turnInfo ), HttpStatus.OK );
    }
}