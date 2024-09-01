package it.unibo.databaseplatform.data;

public class Queries {

    public static final String ALL_DISHES =
            """
            select p.CodicePiatto, p.NomePiatto, p.PrezzoPorzione, p.ApportoCaloricoPorzione, po.Nome
            from piatti p, portate po
            where p.CodicePortata = po.CodicePortata
            """;

    public static final String CLIENT =
            """
            select *
            from clienti c
            where c.CodiceCliente = ?
            and c.Password = ? 
            """;

    public static final String INGREDIENT_DISH =
            """
            select i.Nome
            from composizione_piatti cp, ingredienti i
            where cp.CodicePiatto = ?
            and cp.CodiceIngrediente = i.CodiceIngrediente
            """;

    public static final String ALLERGEN_DISH =
            """
            select a.Nome
            from contiene_allergeni co, allergeni a
            where co.CodicePiatto = ?
            and co.CodiceAllergene = a.CodiceAllergene
            """;

    public static final String GET_BEVERAGES =
            """
            select *
            from bevande b
            """;

    public static final String GET_ORDERS_CODES =
            """
            select o.CodiceOrdine
            from ordini o
            """;

    public static final String INSERT_ORDER =
            """
            insert into ordini
            values ( ?, NULL, ?, CURTIME(), CURDATE(), ?)
            """;

    public static final String INSERT_DISHES_ORDER =
            """
            insert into include_piatti
            values ( ?, ?, ?)
            """;

    public static final String INSERT_BEVERAGES_ORDER =
            """
            insert into include_bevande
            values ( ?, ?, ?)
            """;
}
