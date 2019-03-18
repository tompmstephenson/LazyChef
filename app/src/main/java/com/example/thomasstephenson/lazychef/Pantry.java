import java.util.*;

/**
 * Class that represents the pantry of the user.
 * @author Beatrice Anne Malala
 */
public class Pantry {
	
  /** stores the list of ingredients in the pantry */
	private List<Ingredient> ingredientList = new List<Ingredient>();
	
	/**
	 * Returns the list of ingredients in the pantry.
	 * @return list of ingredients in the pantry
	 */
	public List<Ingredient> getIngredientList() {
		return ingredientList;
	}
	
	/**
	 * Checks if the pantry contains the ingredient.
	 * @param ingredientName the name of the ingredient being checked
	 * @return true if ingredient is in the pantry, false if ingredient is not
	 */
	public boolean checkIngredient(String ingredientName) {
		boolean found = false;
		for(int i = 0; i < ingredientList.size(); i++) {
			if(ingredientList.get(i).getName().equals(ingredientName))
				found = true;
		}
		return found;
	}
	
	/**
	 * Takes a list of ingredients and add them to the current set of ingredients in the pantry.
	 * @param items list of ingredients being added to the pantry
	 */
	public void addIngredients(List<Ingredient> items) {
		ingredientList.addAll(items);
	}
	
	/**
	 * Removes specified ingredient from the pantry.
	 * @param ingredientName ingredient that is being removed from the Pantry
	 */
	public void removeIngredient(String ingredientName) {
		for(int i = 0; i < ingredientList.size(); i++) {
			if(ingredientList.get(i).getName().equals(ingredientName))
				ingredientList.remove(ingredientList.get(i));
		}
	}
	
	/**
	 * Removes all of the ingredients currently saved within the pantry.
	 */
	public void clearPantry() {
		ingredientList.removeAll(ingredientList);
	}
	
}
