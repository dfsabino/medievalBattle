package com.danieleForte.medievalBattle.entity;

import com.danieleForte.medievalBattle.enums.CharacterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Character" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"CHARACTER_ID"})})
@Entity
public class Character implements Serializable {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "CHARACTER_ID", nullable = false )
	private Long id;

	@Column( name = "TITLE", nullable = false )
	private String title;

	@Column( name = "DESCRIPTION" )
	private String description;

	@Column( name = "TYPE_CHARACTER", nullable = false )
	private CharacterType CharacterType;

	@Column( name = "HIT_POINT" )
	private int hitPoint;

	@Column( name = "STRENGTH" )
	private int Strength;

	@Column( name = "DEFENSE" )
	private int Defense;

	@Column( name = "AGILITY" )
	private int agility;

	@Column( name = "QUANTITY_DICE" )
	private int QuantityDice;

	@Column( name = "FACE_DICE" )
	private int faceDice;

	@Column( name = "CREATED_AT", nullable = false )
	private LocalDateTime createdAt;

	@Column( name = "ACTIVE", nullable = false )
	private Boolean active;

}