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

import hr.rokym.musicdbjpa.entity.Artist;
import hr.rokym.musicdbjpa.entity.ArtistDetail;
import hr.rokym.musicdbjpa.service.ArtistDetailService;
import hr.rokym.musicdbjpa.service.ArtistService;

@Controller
@RequestMapping("/artists")
public class ArtistController {

	private ArtistService artistService;
	
	private ArtistDetailService artistDetailService;
		
	@Autowired
	public ArtistController(ArtistService artistService, ArtistDetailService artistDetailService) {
		this.artistService = artistService;
		this.artistDetailService = artistDetailService;
	}
	
	@GetMapping("/list")
	public String listArtists(Model model) {
		
		List<Artist> artists = artistService.findAll();
		
		model.addAttribute("artists", artists);
		
		return "list-artists";
	}
	
	@GetMapping("/showFormForAddArtist")
	public String showFormForAddArtist(Model model) {
		
		Artist artist = new Artist();
		
		model.addAttribute("artist", artist);
		
		return "artist-form";
	}
	
	@PostMapping("/save")
	public String saveArtist(@ModelAttribute("artist") Artist artist) {
		
	    if (artist.getId() == 0) {
	    	
	    	ArtistDetail newDetail = new ArtistDetail();
			
			artistDetailService.save(newDetail);
			
			artist.setArtistDetail(newDetail);
		} 
		
		artistService.save(artist);
		
		return "redirect:/artists/list";
	}
	
	@GetMapping("/showFormForUpdateArtist")
	public String showFormForUpdateArtist(@RequestParam("artistId") int id, Model model) {
		
		Artist artist = artistService.findById(id);
		
		model.addAttribute("artist", artist);
		
		return "artist-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("artistId") int id) {
		
		artistService.deleteById(id);
		
		return "redirect:/artists/list";
	}
}



























