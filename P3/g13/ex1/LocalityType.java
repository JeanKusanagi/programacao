package g13.ex1;

/**
 * LocalityType
 * 
 * @author Pedro Teixeira, 84715, MIECT
 */

public enum LocalityType {
	City, Town, Village, Undefined {
		@Override
		public String toString ()
		{
			return "*Indefinido*";
		}
	};
}
