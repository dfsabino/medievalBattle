package com.danieleForte.medievalBattle.entity;

import com.danieleForte.medievalBattle.enums.TypeAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "BattleTurn" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"BATTLE_TURN_ID"})})
@Entity
public class BattleTurn {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "BATTLE_TURN_ID", nullable = false )
    private Long id;

    @ManyToOne
    @JoinColumn( name = "BATTLE_CHARACTER_ID" )
    private BattleCharacter battleCharacter;

    @Column( name = "TYPE_ACTION" )
    private TypeAction typeAction;

    @Column( name = "RESULT_DICE" )
    private int resultDice;

    @Column( name = "TURN_VALUE" )
    private int turnValue;

    @Column( name = "RECEIVED_DAMAGE" )
    private boolean receivedDamage;

    @Column( name = "FINISH_BATTLE" )
    private Boolean finishBattle;






}
