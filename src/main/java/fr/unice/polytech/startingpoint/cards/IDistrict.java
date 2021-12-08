package fr.unice.polytech.startingpoint.cards;

/*
 * Contract to use districts.
 */
public interface IDistrict{
    /**
     * Return the price of district
     * @return price
     */
    int getPrice();

    /**
     * Return the color of district
     * @return
     */
    Color getColor();

    /**
     * Return the district name
     * It's a Enum
     * @return
     */
    DistrictName getDistrictName();

    /**
     * If is it a wonder, return true
     * @return
     */
    boolean isWonder();

}
