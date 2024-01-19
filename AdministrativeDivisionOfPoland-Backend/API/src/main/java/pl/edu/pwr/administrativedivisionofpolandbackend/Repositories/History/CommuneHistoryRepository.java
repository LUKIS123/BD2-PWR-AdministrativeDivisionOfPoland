package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.History;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.CommuneHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.History.CommuneHistoryData;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommuneHistoryRepository extends JpaRepository<CommuneHistory, Integer> {

    Optional<CommuneHistory> findById(int id);

    @Query(nativeQuery = true, value = """
            select * from historia_gmin\s
            where id_gm = ?1\s
            order by nazwa_gminy\s
            """)
    List<CommuneHistory> getCommuneHistoryByCommuneId(Integer communeId);

    @Query(nativeQuery = true, value = """
            select * from historia_gmin\s
            where id_pow = ?1\s
            order by nazwa_gminy\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CommuneHistory> getCommuneHistoryByCountyId(Integer countyId, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select * from historia_gmin\s
            order by nazwa_gminy\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<CommuneHistory> getAllCommuneHistory(Integer offsetRows, Integer fetchRows);


    @Query(nativeQuery = true, value = """
                        select hg.id_zm as id,
                        hg.id_pow as countyId,
                        	case when pw.nazwa_powiatu is null
                                then
                                    hp.nazwa_powiatu
                                else
                                    pw.nazwa_powiatu
                        end as countyName,
                            hg.id_gm as communeId,
                            hg.nazwa_gminy as communeName,
                            hg.adres_siedziby as address,
                            hg.liczba_ludnosci as population,
                            hg.powierzchnia as area,
                            rg.nazwa_rodzaju as communeType,
                            hg.data_poczatkowa as startDate,
                            hg.data_koncowa as endDate
                        from historia_gmin hg
                        left join powiat pw on hg.id_pow = pw.id_pow
                        left join historia_powiatow hp on hg.id_pow = hp.id_pow
                        inner join rodzaj_gminy rg on hg.id_rodzaj_gminy = rg.id_rodzaju_gminy
                        offset ?1 rows
                        fetch first ?2 row only
            """)
    List<CommuneHistoryData> getAllCommuneHistoryData(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from historia_gmin\s
            """)
    Integer getCommuneHistoryCount();

    @Query(nativeQuery = true, value = """
            select * from historia_gmin\s
            where lower(historia_gmin.nazwa_gminy) like(?1)\s
            order by nazwa_gminy\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CommuneHistory> searchCommuneHistoryByName(String searchPhrase, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from historia_gmin\s
            where lower(historia_gmin.nazwa_gminy) like(?1)\s
            """)
    Integer getCountFromSearchCommuneHistory(String searchPhrase);
}
