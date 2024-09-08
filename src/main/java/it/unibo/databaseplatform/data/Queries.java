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

    public static final String GET_CARDS_NUMBER =
            """
                   select cf.NumeroCarta
                   from carte_fedelta cf
            """;

    public static final String INSERT_CARD =
            """
            insert into carte_fedelta
            values ( ? , 0 )
            """;

    public static final String GET_ALL_CLIENTS_CODE =
            """
            select c.CodiceCliente
            from clienti c
            """;

    public static final String INSERT_CLIENT =
            """
            insert into clienti
            values ( ? , ?, ?, ? ,? , ?, ?)
            """;

    public static final String GET_ALL_ADDRESSES_CODE =
            """
            select i.CodiceIndirizzo
            from indirizzi i
            """;

    public static final String INSERT_ADDRESS =
            """
            insert into indirizzi
            values ( ? , ?, ?, ? ,? , ?)
            """;

    public static final String GET_MOST_ORDERED_DISHES =
            """
            select p.NomePiatto, sum(ip.Quantità) as NumeroOrdini
            from ordini o, include_piatti ip, piatti p
            where o.CodiceOrdine = ip.CodiceOrdine
            and p.CodicePiatto = ip.CodicePiatto
            group by p.CodicePiatto, p.NomePiatto
            order by sum(ip.Quantità) desc
            """;

    public static final String GET_ADMIN =
            """
            select *
            from amministratori a
            where a.CodiceAmministratore = ?
            and a.Password = ?
            """;

    public static final String AVG_PRICE =
            """
            select avg(o.PrezzoTotale) as PrezzoMedio
            from ordini o
            """;

    public static final String ORDERS_LUNCH =
            """
            select count(*) as NumeroOrdini
            from ordini o
            where o.Orario between '11:00:00' and '15:00:00'
            """;

    public static final String ORDERS_DINNER =
            """
            select count(*) as NumeroOrdini
            from ordini o
            where o.Orario between '18:00:00' and '23:00:00'
            """;

    public static final String ADD_POINTS_TO_CARD =
            """
            update carte_fedelta
            set PuntiFedeltà = PuntiFedeltà + ?
            where NumeroCarta = ?
            """;

    public static final String GET_MOST_REVIEWED =
            """
            select p.NomePiatto, avg(r.Voto) as VotoMedio
            from piatti p, recensioni r
            where p.CodicePiatto = r.CodicePiatto
            group by p.CodicePiatto, p.NomePiatto
            order by avg(r.Voto) desc
            """;

    public static final String GET_DISHES_TO_REVIEW =
            """
                    select distinct p.CodicePiatto, p.NomePiatto
                                from include_piatti ip, ordini o, piatti p
                                where o.CodiceCliente = ?
                                and ip.CodiceOrdine = o.CodiceOrdine
                                and p.CodicePiatto = ip.CodicePiatto
                                and not exists ( select *
                                                from recensioni r
                                                where r.CodicePiatto = p.CodicePiatto )
            """;

    public static final String SAVE_REVIEW =
            """
            insert into recensioni
            values ( ?, ? ,? ,?)
            """;

    public static final String GET_ORDERS_FROM_CLIENT =
            """
            select o.CodiceOrdine, o.CodiceSconto, o.PrezzoTotale, o.Orario, o.Data
            from ordini o
            where o.CodiceCliente = ?
            """;

    public static final String GET_DISHES_FROM_ORDER =
            """
            select p.NomePiatto, ip.Quantità
            from include_piatti ip, piatti p
            where ip.CodiceOrdine = ?
            and p.CodicePiatto = ip.CodicePiatto
            """;

    public static final String GET_BEVERAGES_FROM_ORDER =
            """
            select b.Nome, ib.Quantità
            from include_bevande ib, bevande b
            where ib.CodiceOrdine = ?
            and b.CodiceBevanda = ib.CodiceBevanda
            """;

    public static final String GET_CARD_INFORMATION =
            """
            select cf.NumeroCarta, cf.PuntiFedeltà
            from carte_fedelta cf, clienti c
            where c.CodiceCliente = ?
            and c.NumeroCarta = cf.NumeroCarta
            """;

    public static final String GENERATE_DISCOUNT =
            """
            insert into sconti
            values (?, ?, ?, ?)        
            """;

    public static final String GET_ALL_DISCOUNTS =
            """
            select s.CodiceSconto
            from sconti s        
            """;

    public static final String REGISTER_SUPPLY =
            """
            insert into rifornimenti
            values (?, curdate(), ?, ?, ?, ?)      
            """;

    public static final String GET_ALL_SUPPLY_CODES =
            """
            select r.CodiceRifornimento
            from rifornimenti r
            """;

    public static final String GET_INGREDIENTS_CODE =
            """
            select i.CodiceIngrediente
            from ingredienti i
            """;

    public static final String GET_VAT_NUMBERS =
            """
            select f.PartitaIVA
            from fornitori f
            """;

    public static final String GET_SUPPLY_OF_INGREDIENT =
            """
            select r.CodiceRifornimento, r.Data, r.Quantità, r.CodiceAmministratore, i.Nome, f.RagioneSociale
            from rifornimenti r, ingredienti i, fornitori f
            where r.CodiceIngrediente = i.CodiceIngrediente
            and f.PartitaIva = r.PartitaIva
            and r.CodiceIngrediente = ?
            """;

    public static final String INSERT_DISH =
            """
            insert into piatti
            values (?,?,?,?,?)
            """;

    public static final String INSERT_INGREDIENT_OF_DISH =
            """
            insert into composizione_piatti 
            values(?,?)
            """;

    public static final String INSERT_ALLERGENS_OF_DISH =
            """
            insert into contiene_allergeni
            values(?,?)
            """;

    public static final String GET_ALL_INGREDIENTS =
            """
            SELECT *
            from ingredienti
            """;

    public static final String GET_ALL_ALLERGENS =
            """
            select *
            from allergeni
            """;

    public static final String GET_ALL_SERVINGS =
            """
            select *
            from portate
            """;

    public static final String REMOVE_DISH_ALLERGENS =
            """
            delete from contiene_allergeni where CodicePiatto = ?
            """;

    public static final String REMOVE_DISH_INGREDIENTS =
            """
            delete from composizione_piatti where CodicePiatto = ?
            """;

    public static final String REMOVE_DISH =
            """
            delete from piatti where CodicePiatto = ?
            """;
}
