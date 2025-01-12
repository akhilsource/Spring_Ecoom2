import Carousel from 'react-bootstrap/Carousel';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Carousel.css';

function Carousal() {
  return (
    <div className="carousel-container">
      <Carousel className="carousel-custom" interval={3000} controls={true} indicators={true}>
        <Carousel.Item>
          <img className="d-inline w-25 carousel-image" src="/images/download (1).jpeg" alt="slide 1" />
        </Carousel.Item>
        <Carousel.Item>
          <img className="d-flex w-0.3 carousel-image" src="/images/download (2).jpg" alt="slide 2" />
        </Carousel.Item>
        <Carousel.Item>
          <img className="d-inline w-25 carousel-image" src="/images/download (3).jpeg" alt="slide 3" />
        </Carousel.Item>
      </Carousel>
    </div>
  );
}

export default Carousal;
