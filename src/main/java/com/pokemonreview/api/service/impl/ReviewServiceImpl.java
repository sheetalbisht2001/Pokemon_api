package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.exceptions.ReviewNotFoundException;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.models.Review;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.repository.ReviewRepository;
import com.pokemonreview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository){
        this.reviewRepository = reviewRepository ;
        this.pokemonRepository = pokemonRepository ;
    }

    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);

        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(()  -> new PokemonNotFoundException("Pokemon with associated review not found")) ;

        review.setPokemon(pokemon);

        Review newReview = reviewRepository.save(review);

        return mapToDto(newReview);


    }

    @Override
    public List<ReviewDto> getReviewsByPokemonId(int id){
        List<Review> reviews = reviewRepository.findByPokemonId(id) ;

        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList()) ;
    }

    @Override
    public ReviewDto getReviewById(int reviewId, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found")) ;

        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->  new ReviewNotFoundException("Review with associated review not found"));

        if(review.getPokemon().getID() != pokemon.getId()){
            throw new ReviewNotFoundException("This review does not belong to a pokemon") ;
        }

        return mapToDto(review) ;
    }

    private ReviewDto mapToDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId()) ;
        reviewDto.setTitle(review.getTitle()) ;
        reviewDto.setContent(review.getContent()) ;
        reviewDto.setStars(review.getStars()) ;
        return reviewDto ;
    }

    private Review mapToEntity(ReviewDto reviewDto){
        Review review = new Review();
        review.setId(reviewDto.setId()) ;
        review.setTitle(reviewDto.getTitle()) ;
        review.setContent(reviewDto.getContent()) ;
        review.setStars(reviewDto.getStars()) ;
    }
}
