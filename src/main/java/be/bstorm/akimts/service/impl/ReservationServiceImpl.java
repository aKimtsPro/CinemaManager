package be.bstorm.akimts.service.impl;

import be.bstorm.akimts.data.ProjectionRepository;
import be.bstorm.akimts.data.ReservationRepository;
import be.bstorm.akimts.data.impl.FilmDAO;
import be.bstorm.akimts.data.impl.ProjectionDAO;
import be.bstorm.akimts.data.impl.ReservationDAO;
import be.bstorm.akimts.models.dto.ReservationDTO;
import be.bstorm.akimts.models.entities.Projection;
import be.bstorm.akimts.models.entities.Reservation;
import be.bstorm.akimts.models.form.ReservationForm;
import be.bstorm.akimts.service.ReservationService;

import java.util.Comparator;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {


    // region SINGLETON
    private static ReservationServiceImpl instance;
    public static ReservationServiceImpl getInstance(){
        return instance == null ? instance = new ReservationServiceImpl() : instance;
    }
    private ReservationServiceImpl(){}
    // endregion

    private final ReservationRepository reservationRepository = ReservationDAO.getInstance();
    private final ProjectionRepository projectionRepository = ProjectionDAO.getInstance();

    @Override
    public List<ReservationDTO> getReservationOfUtilisateur(String username) {

        List<Reservation> reservations = reservationRepository.getByUtilisateur(username);

        return reservations.stream()
                .map((reservation) -> {
                    ReservationDTO dto = new ReservationDTO();

                    dto.setId( reservation.getId() );
                    dto.setPrix( reservation.getPrix() );
                    dto.setType( reservation.getTypeTicket() );

                    Projection projection = projectionRepository.getOne( reservation.getProjectionId() )
                            .get();

                    dto.setHeure( projection.getHeure() );
                    dto.setTitreFilm( projection.getFilm().getTitre() );

                    return dto;
                })
                .toList();

    }

    @Override
    public void createReservation(ReservationForm form) {
        Reservation reservation = new Reservation();

        reservation.setUtilisateurId( form.getUtilisateurId() );
        reservation.setProjectionId( form.getProjectionId() );
        reservation.setPrix( 8 );
        reservation.setTypeTicket( "CLASSIC" );

        reservationRepository.create( reservation );
    }
}
