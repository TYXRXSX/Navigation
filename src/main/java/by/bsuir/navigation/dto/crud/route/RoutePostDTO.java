package by.bsuir.navigation.dto.crud.route;

import lombok.Data;

import java.util.List;

@Data
public class RoutePostDTO {
    private String name;
    private Double latitude;
    private Double longitude;
    private String description;
    private String type;
    private String surface;
    private Double length;
    private String difficulty;
    private String duration;
    private String weather;
    private List<RouteImage> images;
    private List<RouteWaypointDTO> waypoints;
}
