
class StringUtils {
    public static boolean isPalindrome(String str) {

        if (str.isBlank() || str.isEmpty()) {
            return false;
        }

        String cleanStr = str.toLowerCase().replaceAll("['\\s]+", "");

        StringBuilder stringBuilder = new StringBuilder(cleanStr);

        return cleanStr.equals(stringBuilder.reverse().toString());
    }
}