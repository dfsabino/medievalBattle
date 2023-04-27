package com.danieleForte.medievalBattle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "BattleCharacter" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"BATTLE_CHARACTER_ID"})})
@Entity
public class BattleCharacter {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "BATTLE_CHARACTER_ID", nullable = false )
    private Long id;

    @ManyToOne
    @JoinColumn( name = "CHARACTER_ID" , nullable = true )
    private Character character;

    @Column( name = "NAME" )
    private String name;

    @Column( name = "HIT_POINT" )
    private int hitPoint;

    @ManyToOne
    @JoinColumn( name = "BATTLE_ID" , nullable = true )
    private Battle battle;

    @Column( name = "STRENGTH" )
    private int strength;

    @Column( name = "DEFENSE" )
    private int defense;

    @Column( name = "AGILITY" )
    private int agility;

    private String code_share;
}
