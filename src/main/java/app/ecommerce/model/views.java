package app.ecommerce.model;

public class views {
	
	public static class ViewBase {}	
	
	public static class ViewClient extends ViewBase {}
	
	public static class ViewProduitClient extends ViewClient {}
	
	public static class ViewClientsProduits extends ViewClient  {}
	
	public static class ViewConnexion extends ViewBase{}
}
