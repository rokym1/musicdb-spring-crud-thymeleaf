package hr.rokym.musicdbjpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import hr.rokym.musicdbjpa.service.AlbumService;
import hr.rokym.musicdbjpa.service.ArtistService;
import hr.rokym.musicdbjpa.service.SongService;

@Controller
@RequestMapping("/songs")
public class SongController {

	private SongService songService;
	
	private ArtistService artistService;
	
	private AlbumService albumService;
	
	@Autowired
	public SongController(SongService songService, ArtistService artistService, 
			AlbumService albumService) {
		
		this.songService = songService;
		this.artistService = artistService;
		this.albumService = albumService;
	}
	
	
 }






















