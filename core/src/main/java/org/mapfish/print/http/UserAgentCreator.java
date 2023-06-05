package org.mapfish.print.http;

import org.apache.http.util.VersionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for the creation of the User-Agent string.
 *
 * @author bhoefling
 */
public final class UserAgentCreator {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserAgentCreator.class);

  private static final String AGENT_NAME = "MapFishPrint";

  /** Private constructor. */
  private UserAgentCreator() {}

  /**
   * Builds a User-Agent string.
   *
   * @return User-Agent
   */
  public static String getUserAgent() {

    final String httpClientUserAgent =
        VersionInfo.getUserAgent(
            "Apache-HttpClient", "org.apache.http.client", UserAgentCreator.class);

    // This is based on the code from HttpClient:
    final VersionInfo mapFishPrintVersionInfo =
        VersionInfo.loadVersionInfo("org.mapfish.print", UserAgentCreator.class.getClassLoader());

    String mfpRelease = "0.0.0";
    try {
      mfpRelease = mapFishPrintVersionInfo.getRelease();
    } catch (final Exception e) {
      LOGGER.error("Error getting MapFishPrint version", e);
    }

    final String userAgent = String.format("%s/%s %s", AGENT_NAME, mfpRelease, httpClientUserAgent);
    return userAgent;
  }
}
