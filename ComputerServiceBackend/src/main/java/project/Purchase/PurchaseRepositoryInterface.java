package project.Purchase;

import java.util.List;

public interface PurchaseRepositoryInterface {
	List<Purchase> getPurchases(int userId);

	void addPurchase(Purchase purchase);
}
