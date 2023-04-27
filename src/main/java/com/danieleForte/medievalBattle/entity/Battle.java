package com.danieleForte.medievalBattle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Battle" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"BATTLE_ID"})})
@Entity
public class Battle {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "BATTLE_ID", nullable = false )
    private Long id;

    @Column( name = "CODE_SHARE" )
    private String codeShare;

    @Column( name = "DATE_BATTLE" )
    private LocalDateTime dateBattle;

    @Column( name = "CHARACTER_Id" , nullable = true )
    private long characterWin;

    @Column( name = "QUANTITY_TURN" )
    private int quantityTurn;
}
