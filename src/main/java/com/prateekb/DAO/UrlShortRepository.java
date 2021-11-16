package com.prateekb.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prateekb.model.UrlShort;


@Repository
public interface UrlShortRepository extends JpaRepository<UrlShort, Integer>{

	@Query(value ="select lid from urlshort where urlpath = :urlPath limit 1",nativeQuery=true)
	Integer findByPath(@Param("urlPath") String urlPath);

	@Query(value ="select * from urlshort where lId = :lId limit 1",nativeQuery=true)
	UrlShort findUrlByLId(@Param("lId") Integer lId);

	@Modifying(clearAutomatically = true)
	@Query(value="update urlshort set trmurl = :trmurl where lid = :lId",nativeQuery = true)
	int updateTrmUrl(@Param("trmurl") String trmurl,@Param("lId") Integer lId);
}
