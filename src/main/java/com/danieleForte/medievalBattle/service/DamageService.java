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
    private BattleTurnService service;
    private BattleService battleService;
    private CharacterRepository characterRepository;
    private BattleCharacterRepository battleCharacterRepository;

    public BattleTurn damage(TurnInfo turnInfo ) {
        Character character = new Character();
        BattleTurn battleTurn = new BattleTurn();
        BattleTurn battleTurnDefense = new BattleTurn();
        BattleCharacter bt = new BattleCharacter();
        bt =  battleCharacterRepository.findById(turnInfo.getCharacterBattleId()).orElseThrow( ( ) -> new ResourceNotFoundException(
                "Character not found with ID: " + turnInfo.getCharacterBattleId() ) );

        if(bt!=null) {
            BattleCharacter finalBt = bt;
            character = characterRepository.findById(bt.getCharacter().getId()).orElseThrow( ( ) -> new ResourceNotFoundException(
                    "Character not found with ID: " + finalBt.getCharacter().getId()));

            HistoryPlayDice response = playDice.getDiceResult(character.getFaceDice(), character.getQuantityDice());

            battleTurnDefense = service.getLastDefenseByBattleId(turnInfo.getBattleId());
            battleTurn.setTypeAction(TypeAction.DAMAGE);
            battleTurn.setBattleCharacter(bt);
            int resultDamage = response.getSumDices() + bt.getStrength();

            if(resultDamage >= bt.getHitPoint())
                bt.setHitPoint(bt.getHitPoint()-resultDamage);

            if(bt.getHitPoint()<=0) {
                battleTurn.setFinishBattle(true);
                BattleTurn battleTurnAttack = service.getLastAttackByBattleId(turnInfo.getBattleId());
                Battle battle = battleService.findById(turnInfo.getBattleId());
                battle.setCharacterWin(battleTurn.getBattleCharacter().getId());
                int qtdTurn = service.getQtdTurn(battleTurn.getId());
                battle.setQuantityTurn(qtdTurn);
            }
            else
            {
                battleTurn.setFinishBattle(false);
            }

            battleTurn.setTurnValue(resultDamage);

            battleTurn.setResultDice(response.getSumDices());
        }

        return service.update( battleTurn );
    }
}
