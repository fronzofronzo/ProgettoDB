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
}
