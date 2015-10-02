package com.vmcop.simplethird.findlover;

import com.vmcop.simplethird.findlover.EMF;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "profileinfoendpoint", namespace = @ApiNamespace(ownerDomain = "vmcop.com", ownerName = "vmcop.com", packagePath = "simplethird.findlover"))
public class ProfileInfoEndpoint {
	private static final Logger log = Logger.getLogger(ProfileInfoEndpoint.class.getName()); 
	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listProfileInfo")
	public CollectionResponse<ProfileInfo> listProfileInfo(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit,
			@Nullable @Named("sextype") String sexType,
			@Nullable @Named("randomNum") Integer randomNum
			) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<ProfileInfo> execute = null;
		Integer maxResults = 0;
		
		try {
			mgr = getEntityManager();
			
			/*
			// Dem so luong record thoa dk
			Query countQuery = mgr
					.createQuery("SELECT COUNT(ProfileInfo) FROM ProfileInfo as ProfileInfo "
							+ " WHERE ProfileInfo.userSex = :sexType "
							+ " AND ProfileInfo.bornYear >= :fromYear "
							+ " AND ProfileInfo.bornYear <= :toYear"
							+ " AND ProfileInfo.randomNum <= :randomNum");
			countQuery.setParameter(0, sexType);
			countQuery.setParameter(1, fromYear);
			countQuery.setParameter(2, toYear);
			countQuery.setParameter(3, randomNum);
			
			maxResults = ((Long)countQuery.getSingleResult()).intValue();
			
			log.info("==maxResults==" + maxResults);
			*/
			
			Query query = mgr
					.createQuery("SELECT ProfileInfo FROM ProfileInfo as ProfileInfo "
							+ " WHERE ProfileInfo.userSex = :sexType "
							+ " AND ProfileInfo.randomNum >= :randomNum ");
			
			log.info("==randomNum==" + randomNum);
			
			//log.info("==query==" + query);
			
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setParameter(0, sexType);
				query.setParameter(1, randomNum);
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<ProfileInfo>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null){
				cursorString = cursor.toWebSafeString();
			}
			// for lazy fetch.
			for (ProfileInfo obj : execute);
		} finally {
			mgr.close();
		}

		return CollectionResponse.<ProfileInfo> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}
	
	// Random Integer
	/*
    private static int randInt(int min, int max) {
    	if(max <= 0){
    		return 0;
    	}
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    */

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getProfileInfo")
	public ProfileInfo getProfileInfo(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		ProfileInfo profileinfo = null;
		try {
			profileinfo = mgr.find(ProfileInfo.class, id);
		} finally {
			mgr.close();
		}
		return profileinfo;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param profileinfo the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertProfileInfo")
	public ProfileInfo insertProfileInfo(ProfileInfo profileinfo) {
		EntityManager mgr = getEntityManager();
		try {
			/*
			if (containsProfileInfo(profileinfo)) {
				throw new EntityExistsException("Object already exists");
			}
			*/
			mgr.persist(profileinfo);
		} finally {
			mgr.close();
		}
		return profileinfo;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param profileinfo the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateProfileInfo")
	public ProfileInfo updateProfileInfo(ProfileInfo profileinfo) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsProfileInfo(profileinfo)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(profileinfo);
		} finally {
			mgr.close();
		}
		return profileinfo;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeProfileInfo")
	public void removeProfileInfo(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			ProfileInfo profileinfo = mgr.find(ProfileInfo.class, id);
			mgr.remove(profileinfo);
		} finally {
			mgr.close();
		}
	}

	private boolean containsProfileInfo(ProfileInfo profileinfo) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			ProfileInfo item = mgr
					.find(ProfileInfo.class, profileinfo.getKey());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
