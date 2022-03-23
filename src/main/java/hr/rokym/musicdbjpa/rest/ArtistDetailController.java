package hr.rokym.musicdbjpa.rest;

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
@RequestMapping("/artistDetails")
public class ArtistDetailController {

	
	private ArtistDetailService artistDetailService;
	
	private ArtistService artistService;
	
	@Autowired
	public ArtistDetailController(ArtistDetailService artistDetailService, ArtistService artistService) {
		this.artistDetailService = artistDetailService;
		this.artistService = artistService;
	}
	
	@GetMapping("/showDetails")
	public String showDetails(@RequestParam("artistId") int artistId, Model model) {
		
		Artist artist = artistService.findById(artistId);
		
		ArtistDetail artistDetail = artist.getArtistDetail();
		
		model.addAttribute("artistDetail", artistDetail);
		
		model.addAttribute("artist", artist);
		
		return "details";
	}
	
	@GetMapping("/showFormForUpdateDetails")
	public String showFormForUpdateDetails(@RequestParam("artistDetailId") int id, Model model) {
		
		ArtistDetail artistDetail = artistDetailService.findById(id);
		
		model.addAttribute("artistDetail", artistDetail);
		
		return "detail-form";
	}
	
	@PostMapping("/update")
	public String updateDetail(@ModelAttribute("artistDetail") ArtistDetail artistDetail) {
		
		artistDetailService.save(artistDetail);
		
		return "redirect:/artists/list";
	}
}



























