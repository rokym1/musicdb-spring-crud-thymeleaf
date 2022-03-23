package hr.rokym.musicdbjpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import hr.rokym.musicdbjpa.service.AlbumService;
import hr.rokym.musicdbjpa.service.ArtistService;

@Controller
@RequestMapping("/api")
public class AlbumController {

	private AlbumService albumService;
	
	private ArtistService artistService;
	
	@Autowired
	public AlbumController(AlbumService albumService, ArtistService artistService) {
		this.albumService = albumService;
		this.artistService = artistService;
	}
	
	
}























