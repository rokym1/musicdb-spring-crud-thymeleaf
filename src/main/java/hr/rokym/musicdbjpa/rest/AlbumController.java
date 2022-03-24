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
import hr.rokym.musicdbjpa.service.AlbumService;
import hr.rokym.musicdbjpa.service.ArtistService;

@Controller
@RequestMapping("/albums")
public class AlbumController {

	private AlbumService albumService;
	
	private ArtistService artistService;
	
	@Autowired
	public AlbumController(AlbumService albumService, ArtistService artistService) {
		this.albumService = albumService;
		this.artistService = artistService;
	}
	
	@GetMapping("/list")
	public String listAlbums(Model model) {
		
		List<Album> albums = albumService.findAll();
		
		model.addAttribute("albums", albums);
		
		return "list-albums";
	}
	
	@GetMapping("/showFormForAddAlbum")
	public String showFormForAddAlbum(Model model) {
		
		Album album = new Album();
		
		List<Artist> artists = artistService.findAll();
		
		model.addAttribute("album", album);
		
		model.addAttribute("artists", artists);
		
		return "album-form";
	}
	
	@PostMapping("/save")
	public String saveAlbum(@ModelAttribute("album") Album album) {
		
		Artist tempArtist = artistService.findById(album.getArtist().getId());
		
		album.setArtist(tempArtist);
		
		albumService.save(album);
		
		return "redirect:/albums/list";
	}
	
	@GetMapping("/showFormForUpdateAlbum")
	public String showFormForUpdateAlbum(@RequestParam("albumId") int id, Model model) {
		
		Album album = albumService.findById(id);
		
		model.addAttribute("album", album);
		
		List<Artist> artists = artistService.findAll();
		
		model.addAttribute("artists", artists);
		
		return "album-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("albumId") int id) {
		
		albumService.deleteById(id);
		
		return "redirect:/albums/list";
	}
}



















