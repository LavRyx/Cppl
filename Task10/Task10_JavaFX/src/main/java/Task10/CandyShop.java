package Task10;

/*public class CandyShop {
    public static List<Candy> candies = new ArrayList<>();

    public static double totalCost = 0.0;

    public static void sortCandiesByPrice() {
        candies.sort(Comparator.comparingDouble((Candy c) -> c.getPrice()).reversed());
    }

    public static void addCandy(String name, double pricePerKg) {
        Candy candy = new Candy(name, pricePerKg);
        candies.add(candy);
    }

    public static void deleteCandy(int index) {
        candies.remove(index);
    }


    public static LinkedHashSet<Candy> buyCandies(double budget) {
        totalCost = 0;
        Candy.SetSellNull(candies);
        sortCandiesByPrice();
        LinkedHashSet<Candy> purchasedCandies = new LinkedHashSet<>();

        boolean bought = false;
        int countCandy = 0;

        do {
            for (Candy candy : candies) {
                if (totalCost + candy.price <= budget) {
                    totalCost += candy.price;
                    candy.SetSell();
                    purchasedCandies.add(candy);
                    bought = true;
                }
                else {
                    bought = false;
                }
            }
        } while (bought && purchasedCandies.size() < 2);

        return purchasedCandies;
    }
}*/

