package com.danieleForte.medievalBattle.controller;

import com.danieleForte.medievalBattle.entity.BattleTurn;
import com.danieleForte.medievalBattle.service.BattleTurnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "/battleTurn" )
@Api( value = "MedievalBattle API REST" )
@CrossOrigin( origins = "*" )
public class BattleTurnController {

    @Autowired
    private BattleTurnService service;
    @GetMapping( "/battleTurn" )
    @ApiOperation( "find a Character in DB" )
    public ResponseEntity<List<BattleTurn> > getAll( ) {
        return new ResponseEntity<>( service.findAll( ), HttpStatus.OK );
    }

}
