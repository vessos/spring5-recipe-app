package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expect uom not found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expect uom not found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expect uom not found");
        }

        Optional <UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expect uom not found");
        }

        Optional <UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expect uom not found");
        }

        Optional <UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expect uom not found");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pinUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected category not found");
        }

        Optional<Category>mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe quacRecipe = new Recipe();
        quacRecipe.setDescription("Perfect Guacamole");
        quacRecipe.setCookTime(0);
        quacRecipe.setPrepTime(10);
        quacRecipe.setDifficulty(Difficulty.EASY);
        quacRecipe.setDirections("mess everything");
        Notes notes = new Notes();
        notes.setRecipeNotes("For a very quick quacamole just take a 1/4 cup...");
        notes.setRecipe(quacRecipe);
        quacRecipe.setNotes(notes);


        quacRecipe.getIngredients().add(new Ingredient("ripes avocados",new BigDecimal(2),eachUom,quacRecipe));
        quacRecipe.getIngredients().add(new Ingredient("Kosher salt",new BigDecimal(".5"),teaSpoonUom,quacRecipe));
        quacRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon juice",new BigDecimal(2),tableSpoonUom,quacRecipe));
        quacRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion",new BigDecimal(2),tableSpoonUom,quacRecipe));
        quacRecipe.getIngredients().add(new Ingredient("Cilantro",new BigDecimal(2),tableSpoonUom,quacRecipe));
        quacRecipe.getIngredients().add(new Ingredient("serano chiles, stems and seed removed, minced",new BigDecimal(2),eachUom,quacRecipe));
        quacRecipe.getIngredients().add(new Ingredient("freshly grated black pepper",new BigDecimal(2),dashUom,quacRecipe));
        quacRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed , chopped",new BigDecimal(".5"),eachUom,quacRecipe));

        quacRecipe.getCategories().add(americanCategory);
        quacRecipe.getCategories().add(mexicanCategory);

        recipes.add(quacRecipe);

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy grilled chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirections("Prepare all in one bolw and serve it !!!");

        Notes tacosNote = new Notes();
        tacosNote.setRecipeNotes("We have all products and cook it very fast");
        tacosNote.setRecipe(tacosRecipe);

        tacosRecipe.setNotes(tacosNote);

        tacosRecipe.getIngredients().add(new Ingredient("Ancho Chili Powder",new BigDecimal(2),tableSpoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Dried Oregano",new BigDecimal(1),teaSpoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Dried cumin",new BigDecimal(1),teaSpoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Sugar",new BigDecimal(1),teaSpoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Salt",new BigDecimal(".5"),teaSpoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Olive oil",new BigDecimal(2),tableSpoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("boneless chicken tings",new BigDecimal(5),tableSpoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("chery tomatoes halved",new BigDecimal(5),pinUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("red onion, tinly sliced",new BigDecimal(".25"),eachUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("cup sour cream thinned with 1/4 cup milk ",new BigDecimal(4),cupsUom,tacosRecipe));

        tacosRecipe.getCategories().add(mexicanCategory);
        tacosRecipe.getCategories().add(americanCategory);

        recipes.add(tacosRecipe);

        return recipes;
    }
}
