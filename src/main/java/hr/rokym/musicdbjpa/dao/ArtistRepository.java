package hr.rokym.musicdbjpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.rokym.musicdbjpa.entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

	public List<Artist> findAllByOrderByNameAsc();
	
	public List<Artist> findByNameContainsAllIgnoreCase(String name);
}

