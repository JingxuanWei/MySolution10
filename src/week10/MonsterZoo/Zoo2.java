package week10.MonsterZoo;

import java.util.ArrayList;

public class Zoo2 {
	private ArrayList<Monster> monsters;

	//CONSTRUCTORS
	public Zoo2(int capacity){
		monsters = new ArrayList<Monster>(capacity);
	}

	//ACCESSORS
	public int getCapacity(){ //accessor??? there is no instance variable called capacity
		return monsters.size() + 1; //???
	}

	public int getNumberOfMonsters(){
		return monsters.size();
	}

	//MUTATORS

	//OTHER METHODS
	public int getIndexOfAMonster(String nameToGet){
		int retValue = -1; //default is not found

		for (int i = 0; i < monsters.size(); i++)
			if (monsters.get(i).getName().equals(nameToGet))
				retValue = i;

		return retValue;
	}

	public Monster getAMonsterByIndex(int indexToGet){
		Monster retVal;
		if (indexToGet >= 0 && indexToGet < monsters.size())
			retVal =  monsters.get(indexToGet); //PRIVACY LEAK OUT
		else
			retVal = null;

		return retVal;
	}

	public Monster getAMonsterByName(String nameToGet){
		Monster retVal = null;
		for (int i = 0; i <= monsters.size() - 1; i++)
			if (monsters.get(i).getName().equals(nameToGet))
				retVal = monsters.get(i);

		return retVal; //PRIVACY LEAK OUT
	}

	//an append operation
	public boolean addAMonster(Monster monsterToAdd){
		boolean retValue = true;

		monsters.add(monsterToAdd);

		return retValue;
	}

	//an insert operation
	public boolean insertAMonster(Monster monsterToInsert, int indexToAddAt){
		boolean retValue = true;

		if ((indexToAddAt >= 0 && indexToAddAt <= monsters.size() - 1)){
			monsters.add(indexToAddAt, monsterToInsert); //PRIVACY LEAK IN
		}
		else
			retValue = false;
		return retValue;
	}

	public boolean removeAMonster(int indexToRemove){
		boolean retValue = true;

		if (indexToRemove >= 0 && indexToRemove <= monsters.size() - 1 ){
			monsters.remove(indexToRemove);
		}
		else
			retValue = false;
		return retValue;
	}


	public String toString(){
		String outString;

		outString = monsters.size() + " monsters in the zoo";

		for (int i = 0; i <= monsters.size() -1; i++)
			outString += "\n\t" + monsters.get(i).toString() + "\n";

		return outString;
	}

	public String toStringShort(){
		String outString;

		outString = monsters.size() + " monsters in the zoo\n";

		for (int i = 0; i <= monsters.size() -1; i++)
			outString += monsters.get(i).toStringShort() + "\n";

		return outString;
	}

	public boolean fight(	Monster aggressor, int aggressorLimbsLost, int aggressorEyesLost,
							 Monster victim, int victimLimbsLost, int victimEyesLost){
		boolean success = true;
		int aLimbs, aEyes, vLimbs, vEyes;

		if (getAMonsterByName(aggressor.getName()) != null && getAMonsterByName(victim.getName()) != null){
			aggressor.setTemperament(Monster.TemperamentEnum.AGGRESSIVE);

			aLimbs = aggressor.getNumberOfLimbs() - aggressorLimbsLost;
			if (aLimbs >= 0) {
				aggressor.setNumberofLimbs(aLimbs);
				if (aggressor.getNumberOfLimbs() == 0)
					aggressor.setTemperament(Monster.TemperamentEnum.PASSIVE);
			}
			else
				success = false;

			aEyes = aggressor.getNumberOfEyes() - aggressorEyesLost;
			if (aEyes >= 0) {
				aggressor.setNumberofEyes(aEyes);
			}
			else
				success = false;

			vLimbs = victim.getNumberOfLimbs() - victimLimbsLost;
			if (vLimbs >= 0) {
				victim.setNumberofLimbs(vLimbs);
			}
			else
				success = false;

			vEyes = victim.getNumberOfEyes() - victimEyesLost;
			if (vEyes >= 0) {
				victim.setNumberofEyes(vEyes);
			}
			else
				success = false;

		}
		else
			success = false;



		return success;
	}
}
