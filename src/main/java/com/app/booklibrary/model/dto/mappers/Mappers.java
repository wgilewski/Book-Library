package com.app.booklibrary.model.dto.mappers;


import com.app.booklibrary.model.book.Book;
import com.app.booklibrary.model.dto.BookDto;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


public class Mappers
{

    public BookDto fromBookToBookDto(Book book)
    {

        return BookDto.builder()
                .isbn(checkBookIsbn(book))
                .authors(book.getVolumeInfo().getAuthors())
                .categories(book.getVolumeInfo().getCategories())
                .description(book.getVolumeInfo().getDescription())
                .language(book.getVolumeInfo().getLanguage())
                .pageCount(book.getVolumeInfo().getPageCount())
                .previewLink(book.getVolumeInfo().getPreviewLink())
                .publishedDate(convertDate(book.getVolumeInfo().getPublishedDate()))
                .publisher(book.getVolumeInfo().getPublisher())
                .subtitle(book.getVolumeInfo().getTitle())
                .thumbNailUrl(book.getVolumeInfo().getImageLinks().getThumbnail())
                .title(book.getVolumeInfo().getTitle())
                .averageRating(book.getVolumeInfo().getAverageRating())
                .build();
    }


    public static String checkBookIsbn(Book book)
    {
        Optional<String> isbnOp = book.getVolumeInfo().getIndustryIdentifiers()
                .stream()
                .filter(industryIdentifiers -> industryIdentifiers.getType().equals("ISBN_13"))
                .map(industryIdentifiers -> industryIdentifiers.getIdentifier())
                .findFirst();

        String isbn = "";
        if (isbnOp.isPresent())
        {
            isbn = isbnOp.get();
        }
        else
        {
            isbn = book.getId();
        }

        return isbn;
    }

    public static Long convertDate(String stringDate){

        SimpleDateFormat simpleDateFormat = null;

        if (stringDate == null)
        {
            return null;
        }
        else if (stringDate.matches("[0-9]{4}"))
        {
            simpleDateFormat = new SimpleDateFormat("yyyy");
        }
        else if (stringDate.matches("[0-9]{4}-[0-9]{2}"))
        {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        }
        else simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long unix = date.getTime();

        return unix/1000;
    }
}
