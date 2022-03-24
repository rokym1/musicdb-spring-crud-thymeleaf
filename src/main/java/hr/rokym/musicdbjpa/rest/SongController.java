package hr.rokym.musicdbjpa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hr.rokym.musicdbjpa.entity.Album;
import hr.rokym.musicdbjpa.entity.Artist;
import hr.rokym.musicdbjpa.entity.Song;
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
	
	@GetMapping("/list")
	public String listSongs(Model model) {
		
		List<Song> songs = songService.findAll();
		
		model.addAttribute("songs", songs);
		
		return "list-songs";
	}
	
	@GetMapping("/showFormForAddSong")
	public String showFormForAddSong(Model model) {
		
		Song song = new Song();
		
		List<Album> albums = albumService.findAll();
		
		model.addAttribute("song", song);
		
		model.addAttribute("albums", albums);
		
		return "song-form";
	}
	
	@PostMapping("/save")
	public String saveSong(@ModelAttribute("song") Song song) {
		
		Album tempAlbum = albumService.findById(song.getAlbum().getId());
		
		Artist tempArtist = artistService.findById(tempAlbum.getArtist().getId());
		
		song.setAlbum(tempAlbum);
		
		song.setArtist(tempArtist);
		
		songService.save(song);
		
		return "redirect:/songs/list";
	}
	
	@GetMapping("/delete")
	public String deleteSong(@RequestParam("songId") int id) {
		
		songService.deleteById(id);
		
		return "redirect:/songs/list";
	}
 }

























