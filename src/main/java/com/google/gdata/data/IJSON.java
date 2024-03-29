package com.google.gdata.data;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.james.mime4j.field.datetime.DateTime;

import com.google.gdata.client.Service;

public interface IJSON {
	  /**
	   * Returns the list of all authors on this resource.
	   */
	  public List<? extends IPerson> getAuthors();

	  /**
	   * Returns a set of categories on this resource.
	   */
	  public Set<? extends ICategory> getCategories();

	  /**
	   * Get the unique id for this resource.  Represents the atom:id element.
	   */
	  public String getId();

	  /**
	   * Sets the unique id for this resource.
	   */
	  public void setId(String id);

	  /**
	   * Get a {@link DateTime} instance representing the last time this resource
	   * was updated.  Represents the atom:updated element.
	   */
	  public Date getUpdated();

	  /**
	   * Sets the last time this resource was updated.
	   */
	  public void setUpdated(Date updated);

	  /**
	   * Returns a list of atom:link elements on this resource.  If there are no
	   * links, an empty list will be returned.
	   */
	  public List<? extends ILink> getLinks();

	  /**
	   * Returns a particular atom:link element with the given rel and type, or null
	   * if one was not found.
	   */
	  public ILink getLink(String rel, String type);

	  /**
	   * Adds a link with the given rel, type, and href.
	   */
	  public ILink addLink(String rel, String type, String href);

	  /**
	   * Remove all links that match the given {@code rel} and {@code type} values.
	   *
	   * @param relToMatch {@code rel} value to match or {@code null} to match any
	   *     {@code rel} value.
	   * @param typeToMatch {@code type} value to match or {@code null} to match any
	   *     {@code type} value.
	   */
	  public void removeLinks(String relToMatch, String typeToMatch);
	  
	  /**
	   * Removes all links from the this resource.
	   */
	  public void removeLinks();

	  /**
	   * Returns the atom:title element of this resource.
	   */
	  public ITextConstruct getTitle();

	  /**
	   * Gets the value of the gd:etag attribute for this resource.
	   *
	   * See RFC 2616, Section 3.11.
	   */
	  public String getEtag();

	  /**
	   * Sets the value of the gd:etag attribute for this resource.
	   */
	  public void setEtag(String etag);

	  /**
	   * Returns the value of the gd:kind attribute for this resource.  Returns
	   * {@code null} if the kind attribute is missing.
	   */
	  public String getKind();
	  
	  /**
	   * Sets the value of the gd:kind attribute for this resource.  A value of
	   * {@code null} will remove the kind attribute.
	   */
	  public void setKind(String kind);

	  /**
	   * Version ID. This is a unique number representing this particular
	   * resource. Every update changes the version ID (unless the update
	   * doesn't modify anything, in which case it's permissible for
	   * version ID to stay the same). Services are free to interpret this
	   * string in the most convenient way. Some services may choose to use
	   * a monotonically increasing sequence of version IDs. Other services
	   * may compute a hash of entry properties or feed content and use that.
	   * <p>
	   * This property is only used for services to communicate the current
	   * version ID back to the servlet. It is NOT set when resources are
	   * parsed (either from requests or from arbitrary XML).
	   */
	  public String getVersionId();

	  /**
	   * Sets the versionId.  See {@link #getVersionId()} for a description of what
	   * the versionId is used for.
	   */
	  public void setVersionId(String versionId);


	  /**
	   * Sets the service that this resource is being used with.
	   */
	  public void setService(Service s);
}
