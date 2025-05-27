package org.aibles.feature.booking.framework.configuration;

import org.aibles.adapter.helper.ExcelHelper;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.adapter.repository.EmailHelper;
import org.aibles.business.service.PayPalUseCase;
import org.aibles.feature.booking.adapter.repository.AirportRepository;
import org.aibles.feature.booking.adapter.repository.BenefitRepository;
import org.aibles.feature.booking.adapter.repository.BookingCacheRepository;
import org.aibles.feature.booking.adapter.repository.CustomerPromotionRepository;
import org.aibles.feature.booking.adapter.repository.FareBenefitRepository;
import org.aibles.feature.booking.adapter.repository.FareRepository;
import org.aibles.feature.booking.adapter.repository.FlightRepository;
import org.aibles.feature.booking.adapter.repository.MapHelper;
import org.aibles.feature.booking.adapter.repository.MealRepository;
import org.aibles.feature.booking.adapter.repository.NewsRepository;
import org.aibles.feature.booking.adapter.repository.PassengerAssistanceRepository;
import org.aibles.feature.booking.adapter.repository.PassengerInfoRepository;
import org.aibles.feature.booking.adapter.repository.PaymentRepository;
import org.aibles.feature.booking.adapter.repository.PlaneBenefitRepository;
import org.aibles.feature.booking.adapter.repository.PlaneRepository;
import org.aibles.feature.booking.adapter.repository.PlaneTravelClassRepository;
import org.aibles.feature.booking.adapter.repository.PromotionRepository;
import org.aibles.feature.booking.adapter.repository.RedisPublisher;
import org.aibles.feature.booking.adapter.repository.ReservedMealRepository;
import org.aibles.feature.booking.adapter.repository.ReservedSeatRepository;
import org.aibles.feature.booking.adapter.repository.ReservedTripRepository;
import org.aibles.feature.booking.adapter.repository.SeatRepository;
import org.aibles.feature.booking.adapter.repository.SpecialAssistanceRepository;
import org.aibles.feature.booking.adapter.repository.TicketRepository;
import org.aibles.feature.booking.adapter.repository.TravelClassRepository;
import org.aibles.feature.booking.adapter.repository.TripContactRepository;
import org.aibles.feature.booking.adapter.repository.TripRepository;
import org.aibles.feature.booking.business.usecase.ActivePromotionUseCase;
import org.aibles.feature.booking.business.usecase.CalculatePriceUseCase;
import org.aibles.feature.booking.business.usecase.CapturePurchaseUseCase;
import org.aibles.feature.booking.business.usecase.CheckFlightSeatAvailabilityUseCase;
import org.aibles.feature.booking.business.usecase.CreatePlaneUseCase;
import org.aibles.feature.booking.business.usecase.DeactivePromotionUseCase;
import org.aibles.feature.booking.business.usecase.DeleteZeroQuantityPromotionUsecase;
import org.aibles.feature.booking.business.usecase.FilterTripsUseCase;
import org.aibles.feature.booking.business.usecase.GetAllPromotionUseCase;
import org.aibles.feature.booking.business.usecase.GetBookedTripsUseCase;
import org.aibles.feature.booking.business.usecase.GetDetailPromotionUseCase;
import org.aibles.feature.booking.business.usecase.GetFlightStatusUsecase;
import org.aibles.feature.booking.business.usecase.GetListAirportUsecase;
import org.aibles.feature.booking.business.usecase.GetListPlaneUsecase;
import org.aibles.feature.booking.business.usecase.GetListTravelClassUseCase;
import org.aibles.feature.booking.business.usecase.GetMealByFlightUseCase;
import org.aibles.feature.booking.business.usecase.GetNewsUsecase;
import org.aibles.feature.booking.business.usecase.GetPlaneDetailUseCase;
import org.aibles.feature.booking.business.usecase.GetPlaneFareStatusUsecase;
import org.aibles.feature.booking.business.usecase.GetSpecialAssistanceUsecase;
import org.aibles.feature.booking.business.usecase.GetTripCheckInUseCase;
import org.aibles.feature.booking.business.usecase.GetValidPromotionUseCase;
import org.aibles.feature.booking.business.usecase.ImportAirportUseCase;
import org.aibles.feature.booking.business.usecase.ImportFareUseCase;
import org.aibles.feature.booking.business.usecase.ImportPromotionUseCase;
import org.aibles.feature.booking.business.usecase.ImportTripUseCase;
import org.aibles.feature.booking.business.usecase.OneWayFilterUseCase;
import org.aibles.feature.booking.business.usecase.PurchaseUseCase;
import org.aibles.feature.booking.business.usecase.RegisterPromotionUseCase;
import org.aibles.feature.booking.business.usecase.RoundTripFilterUseCase;
import org.aibles.feature.booking.business.usecase.UpdateAirportUseCase;
import org.aibles.feature.booking.business.usecase.UpdatePlaneUseCase;
import org.aibles.feature.booking.business.usecase.ValidatePromotionUseCase;
import org.aibles.feature.booking.business.usecase.ValidateSeatUseCase;
import org.aibles.feature.booking.business.usecase.impl.ActivePromotionUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.CalculatePriceUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.CapturePurchaseUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.CheckFlightSeatAvailabilityUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.CreatePlaneUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.DeactivePromotionUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.DeleteZeroQuantityPromotionUsecaseImpl;
import org.aibles.feature.booking.business.usecase.impl.FilterTripsUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetAllPromotionUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetBookedTripsUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetDetailPromotionUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetFlightStatusUsecaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetListAirportUsecaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetListPlaneUsecaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetListTravelClassUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetMealByFlightUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetNewsUsecaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetPlaneDetailUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetPlaneFareStatusUsecaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetSpecialAssistanceUsecaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetTripCheckInUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.GetValidPromotionUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.ImportAirportUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.ImportFareUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.ImportPromotionUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.ImportTripUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.OneWayFilterUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.PurchaseUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.RegisterPromotionUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.RoundTripFilterUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.UpdateAirportUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.UpdatePlaneUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.ValidatePromotionUseCaseImpl;
import org.aibles.feature.booking.business.usecase.impl.ValidateSeatUseCaseImpl;
import org.aibles.feature.booking.business.usecase.*;
import org.aibles.feature.booking.business.usecase.impl.*;
import org.aibles.feature.booking.business.usecase.paypal.PurchaseCancelUsecase;
import org.aibles.feature.booking.business.usecase.paypal.impl.PurchaseCancelUsecaseImpl;
import org.aibles.feature.booking.framework.impl.RedisSubscriber;
import org.aibles.quartz.service.QuartzService;
import org.aibles.util.CurrencyUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class BookingConfiguration {

  @Bean
  public GetListAirportUsecase getListAirportUsecase(
      AirportRepository airportRepository) {
    return new GetListAirportUsecaseImpl(airportRepository);
  }

  @Bean
  public GetValidPromotionUseCase getValidPromotionUseCase(
      PromotionRepository promotionRepository,
      CustomerPromotionRepository customerPromotionRepository,
      CustomerRepository customerRepository) {
    return new GetValidPromotionUseCaseImpl(promotionRepository, customerRepository,
        customerPromotionRepository);
  }

  @Bean
  public GetNewsUsecase getNewsUsecase(
      NewsRepository newsRepository) {
    return new GetNewsUsecaseImpl(newsRepository);
  }

  @Bean
  public GetListTravelClassUseCase getListTravelClassUsecase(
      TravelClassRepository travelClassRepository) {
    return new GetListTravelClassUseCaseImpl(travelClassRepository);
  }

  @Bean
  public GetTripCheckInUseCase getTripCheckInUseCase(TicketRepository ticketRepository,
      PassengerInfoRepository passengerInfoRepository, TripRepository tripRepository,
      AirportRepository airportRepository) {
    return new GetTripCheckInUseCaseImpl(ticketRepository, passengerInfoRepository, tripRepository,
        airportRepository);
  }

  @Bean
  public GetFlightStatusUsecase getFlightStatusUsecase(
      FlightRepository flightRepository, AirportRepository airportRepository) {
    return new GetFlightStatusUsecaseImpl(flightRepository, airportRepository);
  }

  @Bean
  public GetSpecialAssistanceUsecase getSpecialAssistanceUsecase(
      SpecialAssistanceRepository specialAssistanceRepository) {
    return new GetSpecialAssistanceUsecaseImpl(specialAssistanceRepository);
  }

  @Bean
  public GetBookedTripsUseCase getBookedTripsUseCase(TripRepository tripRepository) {
    return new GetBookedTripsUseCaseImpl(tripRepository);
  }

  @Bean
  public GetListPlaneUsecase getListPlaneUsecase(
      PlaneRepository planeRepository) {
    return new GetListPlaneUsecaseImpl(planeRepository);
  }

  @Bean
  public FilterTripsUseCase filterTripsUseCase(
      TripRepository tripRepository,
      FlightRepository flightRepository,
      TravelClassRepository travelClassRepository
  ) {
    return new FilterTripsUseCaseImpl(
        tripRepository, flightRepository, travelClassRepository
    );
  }

  @Bean
  public OneWayFilterUseCase oneWayFilterUseCase(FilterTripsUseCase filterTripsUseCase) {
    return new OneWayFilterUseCaseImpl(filterTripsUseCase);
  }

  @Bean
  public RoundTripFilterUseCase roundTripFilterUseCase(FilterTripsUseCase filterTripsUseCase) {
    return new RoundTripFilterUseCaseImpl(filterTripsUseCase);
  }

  @Bean
  public ImportAirportUseCase importAirportUseCase(ExcelHelper excelHelper,
      AirportRepository airportRepository) {
    return new ImportAirportUseCaseImpl(excelHelper, airportRepository);
  }

  @Bean
  public UpdateAirportUseCase updateAirportUseCase(AirportRepository airportRepository) {
    return new UpdateAirportUseCaseImpl(airportRepository);
  }

  @Bean
  public CreatePlaneUseCase createPlaneUseCase(PlaneRepository planeRepository,
      PlaneBenefitRepository planeBenefitRepository, BenefitRepository benefitRepository,
      ExcelHelper excelHelper,
      TravelClassRepository travelClassRepository,
      PlaneTravelClassRepository planeTravelClassRepository, SeatRepository seatRepository) {
    return new CreatePlaneUseCaseImpl(planeRepository, planeBenefitRepository, benefitRepository,
        excelHelper, travelClassRepository, planeTravelClassRepository, seatRepository);
  }

  @Bean
  public GetPlaneDetailUseCase getPlaneDetailUseCase(PlaneRepository planeRepository,
      TravelClassRepository travelClassRepository) {
    return new GetPlaneDetailUseCaseImpl(planeRepository, travelClassRepository);
  }

  @Bean
  public GetMealByFlightUseCase getMealByFlightUseCase(FlightRepository flightRepository,
      MealRepository mealRepository) {
    return new GetMealByFlightUseCaseImpl(flightRepository, mealRepository);
  }

  @Bean
  public ImportFareUseCase importFareUseCase(ExcelHelper excelHelper,
      TravelClassRepository travelClassRepository, FareRepository fareRepository,
      BenefitRepository benefitRepository, FareBenefitRepository fareBenefitRepository) {
    return new ImportFareUseCaseImpl(excelHelper, travelClassRepository, fareRepository,
        benefitRepository, fareBenefitRepository);
  }

  @Bean
  public GetPlaneFareStatusUsecase getPlaneFareStatusUsecase(FareRepository fareRepository,
      FlightRepository flightRepository) {
    return new GetPlaneFareStatusUsecaseImpl(fareRepository, flightRepository);
  }

  @Bean
  public ImportTripUseCase importTripUseCase(ExcelHelper excelHelper, TripRepository tripRepository,
      AirportRepository airportRepository, PlaneRepository planeRepository,
      MealRepository mealRepository, FlightRepository flightRepository) {
    return new ImportTripUseCaseImpl(excelHelper, tripRepository, airportRepository,
        planeRepository, mealRepository, flightRepository);
  }

  @Bean
  public UpdatePlaneUseCase updatePlaneUseCaseRequest(PlaneRepository planeRepository,
      PlaneBenefitRepository planeBenefitRepository, BenefitRepository benefitRepository,
      ExcelHelper excelHelper,
      TravelClassRepository travelClassRepository,
      PlaneTravelClassRepository planeTravelClassRepository, SeatRepository seatRepository) {
    return new UpdatePlaneUseCaseImpl(planeRepository, benefitRepository, planeBenefitRepository,
        seatRepository, excelHelper, travelClassRepository, planeTravelClassRepository);

  }

  @Bean
  public ImportPromotionUseCase importPromotionUseCase(ExcelHelper excelHelper,
      PromotionRepository promotionRepository, RedisPublisher redisPublisher, MapHelper mapHelper) {
    return new ImportPromotionUseCaseImpl(excelHelper, promotionRepository, redisPublisher, mapHelper);
  }

  @Bean
  public RegisterPromotionUseCase registerPromotionUseCase(PromotionRepository promotionRepository,
      CustomerRepository customerRepository,
      CustomerPromotionRepository customerPromotionRepository) {
    return new RegisterPromotionUseCaseImpl(promotionRepository, customerRepository,
        customerPromotionRepository);
  }

  @Bean
  public GetDetailPromotionUseCase getDetailPromotionUseCase(
      PromotionRepository promotionRepository, CustomerRepository customerRepository,
      CustomerPromotionRepository customerPromotionRepository) {
    return new GetDetailPromotionUseCaseImpl(promotionRepository, customerRepository,
        customerPromotionRepository);
  }

  @Bean
  public ValidatePromotionUseCase validatePromotionUseCase(PromotionRepository promotionRepository,
      CustomerPromotionRepository customerPromotionRepository,
      CustomerRepository customerRepository) {
    return new ValidatePromotionUseCaseImpl(promotionRepository, customerPromotionRepository,
        customerRepository);
  }

  @Bean
  public GetAllPromotionUseCase getAllPromotionUseCase(
      PromotionRepository promotionRepository) {
    return new GetAllPromotionUseCaseImpl(promotionRepository);
  }

  @Bean
  public CapturePurchaseUseCase capturePurchaseUseCase(PayPalUseCase payPalUseCase,
      EmailHelper emailHelper,
      PaymentRepository paymentRepository,
      TripRepository tripRepository,
      TicketRepository ticketRepository,
      PassengerInfoRepository passengerInfoRepository,
      CustomerRepository customerRepository) {
    return new CapturePurchaseUseCaseImpl(payPalUseCase, emailHelper, paymentRepository,
        tripRepository, ticketRepository, passengerInfoRepository, customerRepository);
  }

  @Bean
  public PurchaseUseCase purchaseUseCase(TicketRepository ticketRepository,
      BookingCacheRepository bookingCacheRepository,
      CustomerRepository customerRepository, PaymentRepository paymentRepository,
      TripContactRepository tripContactRepository, PassengerInfoRepository passengerInfoRepository,
      PassengerAssistanceRepository passengerAssistanceRepository,
      ReservedSeatRepository reservedSeatRepository, ReservedMealRepository reservedMealRepository,
      ReservedTripRepository reservedTripRepository, PayPalUseCase payPalUseCase,
      MealRepository mealRepository, CurrencyUtil currencyUtil) {
    return new PurchaseUseCaseImpl(ticketRepository, bookingCacheRepository,
        customerRepository, paymentRepository, tripContactRepository, passengerInfoRepository,
        passengerAssistanceRepository, reservedSeatRepository, reservedMealRepository,
        reservedTripRepository, payPalUseCase, mealRepository, currencyUtil);
  }

  @Bean
  public PurchaseCancelUsecase purchaseCancelUsecase(
      PayPalUseCase payPalUseCase,
      PaymentRepository paymentRepository,
      BookingCacheRepository bookingCacheRepository,
      CustomerRepository customerRepository,
      FlightRepository flightRepository,
      ReservedSeatRepository reservedSeatRepository,
      EmailHelper emailHelper) {
    return new PurchaseCancelUsecaseImpl(
        payPalUseCase,
        paymentRepository,
        bookingCacheRepository,
        customerRepository,
        emailHelper,
        flightRepository,
        reservedSeatRepository);
  }

  @Bean
  public CheckFlightSeatAvailabilityUseCase checkFlightSeatAvailabilityUseCase(
      FlightRepository flightRepository, BookingCacheRepository bookingCacheRepository,
      ReservedSeatRepository reservedSeatRepository) {
    return new CheckFlightSeatAvailabilityUseCaseImpl(flightRepository, bookingCacheRepository,
        reservedSeatRepository);
  }

  @Bean
  public CalculatePriceUseCase calculatePriceUseCase(
      MealRepository mealRepository,
      FareRepository fareRepository,
      PromotionRepository promotionRepository,
      BookingCacheRepository redisRepository
  ) {
    return new CalculatePriceUseCaseImpl
        (mealRepository, fareRepository, promotionRepository, redisRepository);
  }

  @Bean
  public ValidateSeatUseCase validateSeatUseCase(
      FlightRepository flightRepository,
      TripRepository tripRepository,
      SeatRepository seatRepository,
      BookingCacheRepository bookingCacheRepository,
      FareRepository fareRepository
  ) {
    return new ValidateSeatUseCaseImpl(
        flightRepository,
        tripRepository,
        seatRepository,
        bookingCacheRepository,
        fareRepository);
  }

  @Bean
  public DeactivePromotionUseCase deactivePromotionUseCase(PromotionRepository promotionRepository,
      QuartzService quartzService) {
    return new DeactivePromotionUseCaseImpl(promotionRepository,
        quartzService);
  }

  @Bean(name = "giveAwayTopic")
  public ChannelTopic giveAwayTopic() {
    return new ChannelTopic("GIVE-AWAY-TOPIC");
  }

  @Bean
  public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory,
      MessageListenerAdapter messageListener,
      ChannelTopic topic) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(messageListener, topic);
    return container;
  }

  @Bean
  public MessageListenerAdapter messageListener(RedisSubscriber subscriber) {
    return new MessageListenerAdapter(subscriber);
  }

  @Bean
  public ActivePromotionUseCase activePromotionUseCase(PromotionRepository promotionRepository,
      QuartzService quartzService) {
    return new ActivePromotionUseCaseImpl(promotionRepository,
        quartzService);
  }

  @Bean
  public DeleteZeroQuantityPromotionUsecase deleteZeroQuantityPromotionUsecase(
      PromotionRepository promotionRepository) {
    return new DeleteZeroQuantityPromotionUsecaseImpl(promotionRepository);
  }

  @Bean
  public ChangeStatusFlightUseCase changeStatusFlightUseCase(FlightRepository flightRepository, BookingCacheRepository bookingCacheRepository) {
    return new ChangeStatusFlightUseCaseImpl(flightRepository, bookingCacheRepository);
  }
}
