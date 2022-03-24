package hr.rokym.musicdbjpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.rokym.musicdbjpa.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

	public List<Album> findAllByOrderByTitleAsc();
}


