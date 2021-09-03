package interview.airbnb;

import java.util.*;

import java.util.HashMap;

public class ReviewAnnotation {

    public static void main(String[] args) {
        Map<String, String> tags  = new HashMap<>();
        tags.put("Airbnb", "company");
        tags.put("San Francisco", "city");

        String review = "I found this great apartment from airbnb in San francisco. I'm gonna stay here for about a month. Thanks a lot AIRBNB!";
        String expected = "I found this great apartment from {company}[airbnb] in {city}[San francisco]. I'm gonna stay here for about a month. Thanks a lot {company}[AIRBNB]!";
        String actual = annotate(review, tags);
        System.out.println(actual);
        System.out.println(expected.equals(actual));

    }

    static class Segment {
        int oIndex;
        String content;
        Segment(int oIndex, String content) {
            this.oIndex = oIndex;
            this.content = content;
        }
    }

    private static String annotate(String review, Map<String, String> tags) {
        Map<Integer, String> annotations = new HashMap<>();
        List<Segment> segments = new ArrayList<>();

        segments.add(new Segment(0, review));

        for (String key : tags.keySet()) {
            String keyToSearch = key.toLowerCase();
            List<Segment> newSegments = new ArrayList<>();

            for (Segment segment : segments) {
                int fIndex = 0;
                String cSegment = segment.content.toLowerCase();
                while (-1 != cSegment.indexOf(keyToSearch, fIndex)) {
                    int index = cSegment.indexOf(keyToSearch, fIndex);
                    String foundStr = review.substring(segment.oIndex + index, segment.oIndex + index + key.length());
                    annotations.put(segment.oIndex + fIndex + index, "{" + tags.get(key) + "}[" + foundStr + "]");
                    newSegments.add(new Segment(segment.oIndex + fIndex, cSegment.substring(fIndex, index)));
                    fIndex = index + key.length();
                }
                newSegments.add(new Segment(segment.oIndex + fIndex, cSegment.substring(fIndex)));
            }
            segments = newSegments;
        }

        StringBuilder result = new StringBuilder();
        for (Segment segment : segments) {
            String currentSegment = review.substring(segment.oIndex, segment.oIndex + segment.content.length());
            result.append(currentSegment);
            int index = segment.oIndex + segment.content.length();
            if (annotations.containsKey(index)) {
                result.append(annotations.get(index));
            }
        }

        return result.toString();
    }
}
