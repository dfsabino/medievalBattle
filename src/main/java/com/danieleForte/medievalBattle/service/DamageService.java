package com.danieleForte.medievalBattle.service;

import com.danieleForte.medievalBattle.enums.TypeAction;
import com.danieleForte.medievalBattle.exception.ResourceNotFoundException;
import com.danieleForte.medievalBattle.entity.*;
import com.danieleForte.medievalBattle.entity.Character;
import com.danieleForte.medievalBattle.repository.BattleCharacterRepository;
import com.danieleForte.medievalBattle.repository.CharacterRepository;
import com.danieleForte.medievalBattle.utils.PlayDice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DamageService {

    @Autowired
    private static PlayDice playDice = new PlayDice();

    @Autowired
    private BattleTurnService service;

    @Autowired
    private BattleService battleService;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private BattleCharacterService battleCharacterService;

    public BattleTurn damage(TurnInfo turnInfo ) {
        Character character = new Character();
        BattleTurn battleTurn = new BattleTurn();
        BattleTurn battleTurnDefense = new BattleTurn();
        BattleCharacter battlecaCharacter = new BattleCharacter();
        battlecaCharacter =  battleCharacterService.findById(turnInfo.getCharacterBattleId());

        if(battlecaCharacter!=null) {

            long idBattleCharacter = battlecaCharacter.getId();
            character = characterRepository.findById(battlecaCharacter.getCharacter().getId()).orElseThrow( ( ) -> new ResourceNotFoundException(
                    "Character not found with ID: " + idBattleCharacter));
            System.out.println("character: "+character);
            HistoryPlayDice response = playDice.getDiceResult(character.getFaceDice(), character.getQuantityDice());

            battleTurnDefense = service.getLastDefenseByBattleId(turnInfo.getBattleId());
            battleTurn.setTypeAction(TypeAction.DAMAGE);
            battleTurn.setBattleCharacter(battlecaCharacter);
            int resultDamage = response.getSumDices() + battlecaCharacter.getStrength();

            System.out.println("Damage: "+resultDamage);
            System.out.println("Soma dados: "+response.getSumDices());


            battlecaCharacter.setHitPoint(battlecaCharacter.getHitPoint()-resultDamage);


            System.out.println(battlecaCharacter);

            if(battlecaCharacter.getHitPoint()<=0) {
                battleTurn.setFinishBattle(true);
                BattleTurn battleTurnAttack = service.getLastAttackByBattleId(turnInfo.getBattleId());
                Battle battle = battleService.findById(turnInfo.getBattleId());
                battle.setCharacterWin(battleTurn.getBattleCharacter().getId());
                int qtdTurn = service.getQtdTurn(battlecaCharacter.getBattle().getId());
                battle.setQuantityTurn(qtdTurn);
                battleService.update(battle);
            }
            else
            {
                battleTurn.setFinishBattle(false);
            }

            battleTurn.setTurnValue(resultDamage);

            battleTurn.setResultDice(response.getSumDices());
        }
        battleCharacterService.update(battlecaCharacter);
        return service.create( battleTurn );
    }
}
