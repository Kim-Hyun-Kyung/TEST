package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.data.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	private final IngredientRepository ingredientRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		/* 
		 * List<Ingredient> ingredients =Arrays.asList(
				new Ingredient("FLTO","Flour Tortilla",Type.WRAP),
				new Ingredient("COTO","Corn Tortilla",Type.WRAP),
				new Ingredient("GRBF","Ground Beef",Type.PROTEIN),
				new Ingredient("CARN","Carnitas",Type.PROTEIN),
				new Ingredient("TMTO","Diced Tomatoes",Type.VEGGIES),
				new Ingredient("LETC","Lettuce",Type.VEGGIES),
				new Ingredient("CHED","Cheddar",Type.CHEESE),
				new Ingredient("JACK","Monterrey Jeck",Type.CHEESE),
				new Ingredient("SLSA","Salsa",Type.SAUCE),
				new Ingredient("SRCR","Sour Cream",Type.SAUCE)
				//db에 저장된 데이터를 담아와야하지만 임시로 생성해줌	
		);
		*/
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i->ingredients.add(i));
		
		Type[] types = Ingredient.Type.values(); //values : 열거된 모든 원소를 배열에 담아 순서대로 반환
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients,type));
		}
		model.addAttribute("taco",new Taco());
		
		return "design";
	}
	
	private List<Ingredient> filterByType(
			List<Ingredient> ingredients, Type type){
		return ingredients
		.stream()
		.filter(x->x.getType().equals(type)) //특정기준으로 걸러낼 수 있다. boolean 결과를 리턴하는 람다표현식이 필요
		.collect(Collectors.toList());
		}
	
	@PostMapping
	public String processDesign(@Valid Taco design,Errors errors) {
		if(errors.hasErrors()) {
			return "design";
		}
		log.info("Processing design: "+design);
		return "redirect:/orders/current";
	}
}
