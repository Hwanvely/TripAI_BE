package com.milkcow.tripai.image.service;

import com.milkcow.tripai.global.dto.DataResponse;
import com.milkcow.tripai.image.domain.Image;
import com.milkcow.tripai.image.dto.ImageRequestDto;
import com.milkcow.tripai.image.dto.ImageResponseData;
import com.milkcow.tripai.image.dto.ImageResponseDto;
import com.milkcow.tripai.image.embedded.Color;
import com.milkcow.tripai.image.repository.ImageRepositoryCustom;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepositoryCustom imageRepository;

    public DataResponse<ImageResponseDto> searchSimilarPlace(ImageRequestDto requestDto) {
        List<String> requestLabelList = requestDto.getLabelList();
        List<Color> requestColorList = requestDto.stringToColor();

        List<Image> labelMatchedList = imageRepository.searchLabelMatch(requestLabelList);

        List<ImageResponseData> recommendList = calculateSimilarImage(requestLabelList, requestColorList,
                labelMatchedList);

        int recommendCount = recommendList.size();
        ImageResponseDto responseDto = ImageResponseDto
                .builder()
                .recommendCount(recommendCount)
                .recommendList(recommendList)
                .build();
        return DataResponse.create(responseDto);
    }

    private List<ImageResponseData> calculateSimilarImage(List<String> requestLabelList,
                                                          List<Color> requestColorList,
                                                          List<Image> labelMatchedList) {
        return labelMatchedList
                .stream()
                .map(i -> new ImageScore(requestLabelList, requestColorList, i))
                .sorted(Comparator.comparing(ImageScore::getScore).reversed())
                .map(ImageScore::toDto)
                .collect(Collectors.toList());
    }
}
